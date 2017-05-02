package models

import javax.inject.Singleton

/**
  * Created by Nick Karaolis on 15/11/16.
  */
case class Resource(resourceID: Int, var value: BigDecimal, name: String, resourceType: String)
