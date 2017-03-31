package services

import javax.inject.Inject

import models._
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
class MongoConnection @Inject()(val reactiveMongoApi: ReactiveMongoApi) {

  def findCollection(collectionName: String) = reactiveMongoApi.database.map(_.collection[JSONCollection](collectionName))

  def findByID(idField: String, idValue: Int, collectionName: String): Future[Option[Resource]] = for {
    resources <- findCollection(collectionName)
    resource <- resources.find(Json.obj(idField -> idValue)).one[Resource]
  } yield resource

}
