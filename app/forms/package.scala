/**
  * Created by Nick Karaolis on 07/04/17.
  */
package object forms {

  /**
    * Helper method for form validating
    * Only checks the second statement if the first is true
    * Useful when wanting to only display one error message at a time
    */
  def conditionValidate(condition: Boolean, statement: Boolean): Boolean = {
    if(condition) statement else true
  }

  val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"""

}
