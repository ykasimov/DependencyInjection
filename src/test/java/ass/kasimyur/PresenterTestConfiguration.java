package ass.kasimyur;

import ass.kasimyur.Interfaces.IView;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

/**
 * Created by username on 3/16/17.
 */
@Configuration
public class PresenterTestConfiguration {

    @Bean
    @Primary
    public IView testView(){
        return Mockito.mock(IView.class);
    }

}
