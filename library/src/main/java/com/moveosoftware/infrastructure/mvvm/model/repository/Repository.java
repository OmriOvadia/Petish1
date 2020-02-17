package com.moveosoftware.infrastructure.mvvm.model.repository;

import com.moveosoftware.infrastructure.mvvm.model.database.RealmController;
import com.moveosoftware.infrastructure.mvvm.model.network.ApiController;

/**
 * Created by oferdan-on on 8/26/17
 * A repository's purpose is to handle the data loading / fetching, and to pass on the result to a certain presenter
 */

public abstract class Repository<AC extends ApiController, RC extends RealmController> {
    /**
     * Responsible for data fetching from remote sources
     */
    protected AC mApiController = getApiController();
    /**
     * Responsible for data loading from local disk
     */
    protected RC mRealmController = getRealmController();

    /**
     * Returns the relevant implementation of {@link ApiController}
     *
     * @return AC - An implementation of {@link ApiController}
     */
    public abstract AC getApiController();

    /**
     * Returns the relevant implementation of {@link RealmController}
     *
     * @return RC - An implementation of {@link RealmController}
     */
    public abstract RC getRealmController();




}
