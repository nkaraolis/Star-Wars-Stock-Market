package controllers

import javax.inject.{Inject, Singleton}

import models.{LoginForm, TraderDetailsForm}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent, Controller}
import services.TraderAccountService

import scala.concurrent.Future
import scala.util.Random

/**
  * Created by Nick Karaolis on 21/11/16.
  */

@Singleton
class TraderAccountController @Inject()(val messagesApi: MessagesApi,
                                        traderAccountService: TraderAccountService) extends Controller with I18nSupport {

  def displayTraderDetails = Action { implicit request =>
    request.headers.get("Authorization") match {
      case None => Redirect(routes.TraderAccountController.displayLogin())
      case Some(_) => Ok(views.html.user.information.traderDetails(traderAccountService.retrieveTraderDetails))
    }
  }

  def submitUpdateTraderDetails = TODO

  def displayRegistration = Action { implicit request =>
    Ok(views.html.user.information.registration(TraderDetailsForm.traderDetailsForm))
  }

  def submitRegistration = TODO

  def displayLogin = Action { implicit request =>
    Ok(views.html.user.information.login(LoginForm.loginForm))
  }

  def submitLogin: Action[AnyContent] = Action.async { implicit request =>
    LoginForm.loginForm.bindFromRequest().fold(
      formWithErrors => Future.successful(BadRequest(views.html.user.information.login(formWithErrors))),
      validFormData => Future.successful(Redirect(routes.HomeController.home()).withHeaders(AUTHORIZATION -> Random.nextString(50))))
  }
}
