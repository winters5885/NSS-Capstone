package com.nashss.se.ascendnashville.dependency;

import com.nashss.se.ascendnashville.activity.*;

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

    CreateEventActivity provideCreateEventActivity();
}
