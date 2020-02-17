package com.moveosoftware.infrastructure.mvvm.model.database;

import com.moveosoftware.infrastructure.mvvm.model.network.Response;

import io.reactivex.Observable;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by oferdan-on on 8/26/17
 */

public abstract class CrudRealmController<T extends RealmObject, R extends Response> extends RealmController<T> {

    private Class<T> clazz;
    private String primaryKey;

    public CrudRealmController(Class<T> clazz) {
        this.clazz = clazz;
        this.primaryKey = getPrimaryKey();
    }

    /**
     * Loads a locally saved {@link RealmObject} using its {@link io.realm.annotations.PrimaryKey}
     *
     * @param id - the object's {@link io.realm.annotations.PrimaryKey} value
     * @return - the locally saved {@link RealmObject}
     */
    public T getItem(String id) {
        return mRealm.where(clazz)
                .equalTo(primaryKey, id)
                .findFirst();
    }

    /**
     * Loads a locally saved list of {@link RealmObject} using a {@link RealmQuery}
     *
     * @param query - user built {@link RealmQuery} - in order to provide flexible and dynamic querying
     * @return - list of locally saved {@link RealmObject} who match the given {@link RealmQuery}
     */
    public RealmResults<T> getList(RealmQuery<T> query) {
        return query.findAll();
    }

    /**
     * Loads all locally saved list of {@link RealmObject} from a certain class
     *
     * @return - all of the class' saved {@link RealmResults}
     */
    public RealmResults<T> getList() {
        return mRealm.where(clazz)
                .findAll();
    }

    /**
     * Deletes a locally saved {@link RealmObject} according to its {@link io.realm.annotations.PrimaryKey}
     *
     * @param id - the to be deleted {@link RealmObject} {@link io.realm.annotations.PrimaryKey}
     */
    public void delete(String id) {
        mRealm.executeTransaction(realm -> {
            mRealm.where(clazz)
                    .equalTo(primaryKey, id)
                    .findFirst().deleteFromRealm();
        });
    }

    /**
     * Updates a locally saved {@link RealmObject}
     *
     * @param r - the {@link RealmObject} to be updated
     */
    public void update(R r) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(map(r)));
    }

    public T updateWithObject(R r) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(CrudRealmController.this.map(r)));
        return CrudRealmController.this.map(r);
    }

//
//    /**
//     *
//     * @param stream - List of response item
//     * @return - List of Realm managed object
//     */
//    public Flowable<List<T>> updateListByObservable(Flowable<List<R>> stream) {
//        return stream
//                .flatMap(res -> Flowable.from(res)
//                        //Update response to realm and return realm manged object
//                        .map(this::updateWithObject)
//                        //return as realm manged object list
//                        .toList()
//                );
//
//    }

    /**
     *
     * @param stream - Only one response item
     * @return - Realm managed object
     */
    public Observable<T> updateItemByObservable(Observable<R> stream) {
       return  stream
                .map(this::updateWithObject);
    }


    /**
     * Creates a local copy of a given object
     *
     * @param t - the object to be created locally
     */
    public void create(T t) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(t);
        });
    }

    /**
     * This method provides an easy way to declare the identifying {@link io.realm.annotations.PrimaryKey} of the {@link RealmObject}
     * that the inherited implementation is oriented around
     *
     * @return String - the object's {@link io.realm.annotations.PrimaryKey} field name
     */
    protected abstract String getPrimaryKey();

    protected abstract T map(R r);
}
