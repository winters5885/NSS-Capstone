package com.nashss.se.ascendnashville.dependency;

import com.nashss.se.ascendnashville.activity.CreateMemberActivity;
import com.nashss.se.ascendnashville.activity.CreateRouteActivity;
import com.nashss.se.ascendnashville.activity.GetMemberActivity;
import com.nashss.se.ascendnashville.activity.GetRouteActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DynamoDBModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    GetMemberActivity provideGetMemberActivity();

    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */
    CreateMemberActivity provideCreateMemberActivity();
    GetRouteActivity provideGetRouteActivity();
    CreateRouteActivity provideCreateRouteActivity();
}
