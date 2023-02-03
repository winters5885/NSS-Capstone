package ascendnashville.dependency;

import ascendnashville.activity.CreateMemberActivity;
import ascendnashville.activity.GetMemberActivity;

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
}
