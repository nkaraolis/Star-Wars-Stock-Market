package models

import javax.inject.Singleton

/**
  * Created by Nick Karaolis on 15/11/16.
  */
case class Resource(resourceID: Int = 0, var value: BigDecimal = BigDecimal.exact("0"), name: String = "", resourceType: String = "resource")

class Alum extends Resource(resourceID = 1, name = "Alum")

class Chromium extends Resource(resourceID = 2, name = "Chromium")

class Coal extends Resource(resourceID = 3, name = "Coal")

class Exonium extends Resource(resourceID = 4, name ="Exonium")

class Ionite extends Resource(resourceID = 5, name ="Ionite")

class LightsaberCrystal extends Resource(resourceID = 6, name ="LightsaberCrystal")

class Ore extends Resource(resourceID = 7, name ="Ore")

class Spice extends Resource(resourceID = 8, name ="Spice")

class Stygium extends Resource(resourceID = 9, name ="Stygium")

class Thorilide extends Resource(resourceID = 10, name ="Thorilide")

class RepublicCredit extends Resource(resourceID = 11, name ="RepublicCredit", resourceType = "currency")

class Wupiupi extends Resource(resourceID = 12, name ="Wupiupi", resourceType = "currency")

class Druggat extends Resource(resourceID = 13, name ="Druggat", resourceType = "currency")

class NovaCrystal extends Resource(resourceID = 14, name ="NovaCrystal", resourceType = "currency")