package controllers

import javax.inject.{Inject, Singleton}

import models.{LoginForm, TraderDetailsForm}
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, AnyContent, Controller, Flash}
import services.TraderAccountService

import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 21/11/16.
  */

@Singleton
class TraderAccountController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  val traderAccountService = new TraderAccountService

  def displayTraderDetails = Action {
    Ok(views.html.user.information.traderDetails(traderAccountService.retrieveTraderDetails))
  }

  def submitUpdateTraderDetails = TODO

  def displayRegistration = Action { implicit request =>
    Ok(views.html.user.information.registration(TraderDetailsForm.traderDetailsForm))
  }

  def submitRegistration = TODO

  def displayLogin = Action { implicit request =>
    Ok(views.html.user.information.login(LoginForm.loginForm))
  }

  def submitLogin:Action[AnyContent] = Action.async { implicit request =>
    LoginForm.loginForm.bindFromRequest().fold(
      formWithErrors => Future.successful(BadRequest(views.html.user.information.login(formWithErrors))),
      validFormData => Future.successful(Redirect(routes.HomeController.home()))
    )
  }
}
