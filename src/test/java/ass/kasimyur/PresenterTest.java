package ass.kasimyur;


import ass.kasimyur.Interfaces.IMessageRepository;
import ass.kasimyur.Interfaces.IView;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;


/**
 * Created by username on 3/14/17.
 */
//@ActiveProfiles("test")
@ContextConfiguration(classes = PresenterTestConfiguration.class)
public class PresenterTest {
    @Autowired
    IView testView = Mockito.mock(IView.class);

    IMessageRepository testRepo = Mockito.mock(IMessageRepository.class);
    String testString;
    Presenter testPresenter;


    @BeforeTest
    public void setUpTest() {
        Mockito.reset(testView);
        Mockito.reset(testRepo);
        testPresenter = new Presenter(testRepo);
        testString = "test string";

    }

    @Test
    public void testNewMessage() {
        when(testView.getNewMessageText()).thenReturn(testString);
        when(testRepo.add(testString)).thenReturn(true);
        Assert.assertEquals(testString, testView.getNewMessageText());

    }

}
