/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package io.swagger.client.api

import io.swagger.client.model.Game
import io.swagger.client.core._
import io.swagger.client.core.CollectionFormats._
import io.swagger.client.core.ApiKeyLocations._

object DefaultApi {

  /**
   * 
   * 
   * Expected answers:
   *   code 200 : Game (OK)
   * 
   * @param gameId 
   */
  def gamesGameIdGet(gameId: String): ApiRequest[Game] =
    ApiRequest[Game](ApiMethods.GET, "https://virtserver.swaggerhub.com/teemukin/tarinapeli/1.0.0", "/games/{game_id}", "application/json")
      .withPathParam("game_id", gameId)
      .withSuccessResponse[Game](200)
        /**
   * published games for anonymous, logged in user get&#39;s games where (s)he is a player, too
   * 
   * Expected answers:
   *   code 200 : Seq[Game] (OK)
   */
  def gamesGet(): ApiRequest[Seq[Game]] =
    ApiRequest[Seq[Game]](ApiMethods.GET, "https://virtserver.swaggerhub.com/teemukin/tarinapeli/1.0.0", "/games", "application/json")
      .withSuccessResponse[Seq[Game]](200)
        /**
   * This operation shows how to override the global security defined above, as we want to open it up for all users.
   * 
   * Expected answers:
   *   code 200 :  (OK)
   */
  def pingGet(): ApiRequest[Unit] =
    ApiRequest[Unit](ApiMethods.GET, "https://virtserver.swaggerhub.com/teemukin/tarinapeli/1.0.0", "/ping", "application/json")
      .withSuccessResponse[Unit](200)
      

}

