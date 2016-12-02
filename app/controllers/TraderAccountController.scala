package controllers

import javax.inject.{Inject, Singleton}

import models.{LoginForm, TraderDetailsForm}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.TraderAccountService

/**
  * Created by Nick Karaolis on 21/11/16.
  */

@Singleton
class TraderAccountController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  val traderAccountService = new TraderAccountService

  def displayDetails = Action {
    Ok(views.html.user.information.traderDetails(traderAccountService.retrieveTraderDetails))
  }

  def submitUserDetailsUpdate = TODO

  def registration = Action { implicit request =>
    Ok(views.html.user.information.registration(TraderDetailsForm.traderDetailsForm))
  }

  def submitRegistration = TODO

  def login = Action { implicit request =>
    Ok(views.html.user.information.login(LoginForm.loginForm))
  }

  def submitLogin = TODO
}
