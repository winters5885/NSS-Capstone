package ascendnashville.dependency;

import ascendnashville.activity.CreateMemberActivity;
import ascendnashville.activity.GetMemberActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DynamoDBModule.class})
public interface ServiceComponent {

    GetMemberActivity provideGetMemberActivity();

    CreateMemberActivity provideCreateMemberActivity();
}
