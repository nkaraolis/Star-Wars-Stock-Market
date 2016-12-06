package controllers

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.i18n.Messages.Implicits._
import play.api.i18n.{DefaultMessagesApi, Messages, MessagesApi}
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Nick Karaolis on 02/12/16.
  */
class TraderAccountControllerSpec extends PlaySpec with OneAppPerTest {

  val application = new GuiceApplicationBuilder()
    .bindings(bind[MessagesApi].to[DefaultMessagesApi])

  val validLoginForm = Seq(
    "username" -> "test",
    "password" -> "password"
  )

  "TraderAccountController" should {
    "load login page" in {
      val loginPage = route(app, FakeRequest(GET, "/login")).get
      status(loginPage) mustBe OK
      contentAsString(loginPage) must include (Messages("login.title"))
    }
    "submit valid login form and redirect to home page" in {
      val login = route(app, FakeRequest(POST, "/login").withFormUrlEncodedBody(validLoginForm:_*)).get

      redirectLocation(login) must be(Some(routes.HomeController.home().url))
    }
  }
}
