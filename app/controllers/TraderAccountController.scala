package controllers

import javax.inject.{Inject, Singleton}

import forms.{LoginForm, RegistrationForm}
import models.Trader
import play.api.Logger
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Action, AnyContent, Controller}
import services.{MongoConnection, TraderAccountService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

/**
  * Created by Nick Karaolis on 21/11/16.
  */

@Singleton
class TraderAccountController @Inject()(val messagesApi: MessagesApi,
                                        traderAccountService: TraderAccountService,
                                        mongoConnection: MongoConnection) extends Controller with I18nSupport {

  def displayTraderDetails = Action { implicit request =>
    request.headers.get("Authorization") match {
      case None => Redirect(routes.TraderAccountController.displayLogin())
      case Some(_) => Ok(views.html.user.information.traderDetails(traderAccountService.retrieveTraderDetails))
    }
  }

  def submitUpdateTraderDetails = TODO

  def displayRegistration = Action { implicit request =>
    Ok(views.html.user.information.registration(RegistrationForm.registrationForm))
  }

  def submitRegistration: Action[AnyContent] = Action.async { implicit request =>
    RegistrationForm.registrationForm.bindFromRequest().fold(
      formWithErrors => Future.successful(BadRequest(views.html.user.information.registration(formWithErrors))),
      validForm =>
        mongoConnection.incrementID("traders").flatMap {
          traderID =>
            Logger.debug(s"New traderID created with value: $traderID")
            val newTrader = Json.toJson(Trader(traderID, validForm._1, validForm._2, validForm._3, validForm._4, validForm._5)).as[JsObject]
            mongoConnection.insertDocument("traders", newTrader).flatMap {
              result =>
                if (result._1) {
                  Future.successful(Redirect(routes.TraderAccountController.displayLogin()))
                } else {
                  Future.successful(Redirect(routes.TraderAccountController.displayRegistration()))
                }
            }
        }
    )
  }

  def displayLogin = Action { implicit request =>
    Ok(views.html.user.information.login(LoginForm.loginForm))
  }

  def submitLogin: Action[AnyContent] = Action.async { implicit request =>
    LoginForm.loginForm.bindFromRequest().fold(
      formWithErrors => Future.successful(BadRequest(views.html.user.information.login(formWithErrors))),
      _ => Future.successful(Redirect(routes.HomeController.home()).withHeaders(AUTHORIZATION -> Random.nextString(50))))
  }

  def displayOrders: Action[AnyContent] = Action.async { implicit request =>
    //TODO: Find the orders for the currently logged in user
    Future.successful(Ok)
  }

}
