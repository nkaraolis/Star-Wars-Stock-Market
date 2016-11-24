package controllers

import javax.inject.{Inject, Singleton}

import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import play.api.mvc.{Action, Controller}

/**
  * Created by Nick Karaolis on 24/11/16.
  */

@Singleton
class HomeController @Inject() extends Controller {

  def home = Action {
    Ok(views.html.homepage())
  }
}
