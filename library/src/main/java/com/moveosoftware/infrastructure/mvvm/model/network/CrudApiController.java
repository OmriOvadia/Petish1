package com.moveosoftware.infrastructure.mvvm.model.network;

import java.util.List;

import io.reactivex.FlowableSubscriber;


/**
 * Created by oferdan-on on 8/26/17
 */

public abstract class CrudApiController<A, Req extends Request,Res extends Response> extends ApiController<A> {

    public abstract void create(Req request,FlowableSubscriber<Res> subscriber);

    public abstract void getItem(String id, FlowableSubscriber<Res> subscriber);

    public abstract void getList(FlowableSubscriber<List<Res>> subscriber);

    public abstract void delete(String id, FlowableSubscriber<Res> subscriber);

    public abstract void update(String id, FlowableSubscriber<Res> subscriber);


}
