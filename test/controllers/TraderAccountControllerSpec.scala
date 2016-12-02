package controllers

import org.scalatestplus.play.PlaySpec
import play.api.i18n.MessagesApi
import play.api.mvc.Results
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.test.WithApplication

/**
  * Created by Nick Karaolis on 02/12/16.
  */
class TraderAccountControllerSpec extends PlaySpec with Results {

  val validLoginForm = Tuple2("test", "password")

  "TraderAccountController" should {
    "submit valid login form and redirect to home page" in new WithApplication {
      val myCode = app.injector.instanceOf(classOf[MessagesApi])
      val controller = new TraderAccountController(myCode)

      val result = controller.submitLogin().apply(FakeRequest().withFormUrlEncodedBody(validLoginForm))
      redirectLocation(result).get mustEqual routes.HomeController.home().url
    }
  }
}
