package kidsTeacher.english.words

import com.bootstrap.colors.Color
import com.bootstrap.colors.backgroundColor
import com.bootstrap.components.html.Body
import com.bootstrap.components.layout.HorizontalAlignment
import com.bootstrap.components.layout.Row
import com.bootstrap.components.layout.VerticalAlignment
import com.bootstrap.dimens.Height
import com.bootstrap.dimens.height
import com.bootstrap.modifier.Modifier
import com.bootstrap.modifier.margin
import com.mongodb.client.model.Filters.eq
import common.addBootstrapAndCommon
import common.addCommonNavigation
import data.*
import db.nosql.controller.BaseNoSqlCrudController
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.script
import kotlinx.serialization.json.Json
import network.ResponseWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class WordsController(route: Route) : BaseNoSqlCrudController<WordUiData>() {

    private val logger: Logger = LoggerFactory.getLogger(WordsController::class.java)

    init {
        addRoutes(route)
    }

    private fun addRoutes(route: Route) {
        route.apply {
            get("/en/api/words") {
                call.respond(ResponseWrapper(getAll<WordUiData>()))
            }
            get("/en/api/groups") {
                call.respond(
                    ResponseWrapper(
                        getAll<GroupedWordsUiData>().map {
                            GroupUiData(it.group, en = it.group)
                        })
                )
            }

            post("/en/api/words") {
                val data = call.receive<WordUiData>()
                save(data)
                call.respond(getResponseWrapper())
            }

            get("/en/api/words/:id") {
                call.parameters["id"]?.let { id ->
                    call.respond(ResponseWrapper<WordUiData>(findById(id)))
                } ?: run {
                    call.respond(getErrorWrapper("ID is required"))
                }
            }

            get("/en/api/words/{group}") {
                call.parameters["group"]?.let { group ->
                    call.respond(ResponseWrapper<List<WordUiData>>(find(eq(WordUiData::group.name, group))))
                } ?: run {
                    call.respond(getErrorWrapper("group is required"))
                }
            }

            get("/en/api/persistStaticWords") {
                val dictionaryUiData = Json.decodeFromString<EnglishDictionaryUiData>(wordsData)
                save(dictionaryUiData.groups)
//                dictionaryUiData.groups.forEach { group ->
//
////                    transaction {
////                        WordGroupEntity.new {
////                            en = group.group
////                        }
////                    }
////                    transaction {
//                    save(group.words.map {
//                        it.group = group.group
//                        it
//                    })
////                        rollback()
////                    }
//                }
                call.respond(getResponseWrapper())
            }

            get("/en/words") {
                call.respondHtml {
                    head {
                        addBootstrapAndCommon {
                            script(src = "/js/English.js") {}
                        }
                    }
                    Body(Modifier.height(Height.H_AUTO).backgroundColor(Color.GRAY_DARK)) {
                        id = "en-words-page"
                        addCommonNavigation()
                        Row(
                            modifier = Modifier.margin(top = 4),
                            hAlignment = HorizontalAlignment.CENTER,
                            vAlignment = VerticalAlignment.CENTER
                        ) {
                            id = "en-words"

                        }
                    }
                }
            }
        }
    }
}