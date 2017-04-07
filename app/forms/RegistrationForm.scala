package forms

import play.api.data.Form
import play.api.data.Forms.{text, tuple}
import play.api.data.validation.Constraints

/**
  * Created by Nick Karaolis on 07/04/17.
  */
//TODO: Add verification checks for existing username/email and password strength
object RegistrationForm {
  val registrationForm = Form(
    tuple(
      "First Name" -> text.verifying("registration.firstname.required", _.nonEmpty)
        .verifying("registration.firstname.capital.required", x => x.matches("^[A-Z].*")),
      "Last Name" -> text.verifying("registration.lastname.required", _.nonEmpty)
        .verifying("registration.lastname.capital.required", x => x.matches("^[A-Z].*")),
      "Email" -> text.verifying("registration.email.required", _.nonEmpty)
        .verifying("registration.email.format", x => x.matches(emailRegex)),
      "Username" -> text,
      "Password" -> text
    )
  )
}

//TODO: Add verification checks to see if username exists and whether password matches
object LoginForm {
  val loginForm = Form(
    tuple(
      "Username" -> text.verifying("login.username.required", _.nonEmpty),
      "Password" -> text.verifying("login.password.required", _.nonEmpty)
    )
  )
}