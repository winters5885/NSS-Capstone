package com.nashss.se.ascendnashville.dependency;

import com.nashss.se.ascendnashville.activity.CreateEventActivity;
import com.nashss.se.ascendnashville.activity.CreateMemberActivity;
import com.nashss.se.ascendnashville.activity.CreateRouteActivity;
import com.nashss.se.ascendnashville.activity.DeleteEventActivity;
import com.nashss.se.ascendnashville.activity.DeleteRouteActivity;
import com.nashss.se.ascendnashville.activity.GetAllEventsActivity;
import com.nashss.se.ascendnashville.activity.GetEventActivity;
import com.nashss.se.ascendnashville.activity.GetMemberActivity;
import com.nashss.se.ascendnashville.activity.GetRouteActivity;
import com.nashss.se.ascendnashville.activity.UpdateEventActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Ascend Nashville Service.
 */
@Singleton
@Component(modules = {DynamoDBModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetMemberActivity
     */
    GetMemberActivity provideGetMemberActivity();

    /**
     * Provides the relevant activity.
     * @return CreateMemberActivity
     */
    CreateMemberActivity provideCreateMemberActivity();

    /**
     * Provides the relevant activity.
     * @return GetRouteActivity
     */
    GetRouteActivity provideGetRouteActivity();

    /**
     * Provides the relevant activity.
     * @return CreateRouteActivity
     */
    CreateRouteActivity provideCreateRouteActivity();

    /**
     * Provides the relevant activity.
     * @return DeleteRouteActivity
     */
    DeleteRouteActivity provideDeleteRouteActivity();

    /**
     * Provides the relevant activity.
     * @return GetAllEventsActivity
     */
    GetAllEventsActivity provideGetAllEventsActivity();

    /**
     * Provides the relevant activity.
     * @return GetEventActivity
     */
    GetEventActivity provideGetEventActivity();

    /**
     * Provides the relevant activity.
     * @return CreateEventActivity
     */
    CreateEventActivity provideCreateEventActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateEventActivity
     */
    UpdateEventActivity provideUpdateEventActivity();

    /**
     * Provides the relevant activity.
     * @return DeleteEventActivity
     */
    DeleteEventActivity provideDeleteEventActivity();
}
