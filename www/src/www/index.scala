package www

import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.JSON

object Shiki {
  @js.native
  @JSImport("shiki", "codeToHtml")
  def codeToHtml(code: String, options: js.Dynamic): js.Promise[String] = js.native
}

@js.native
@JSImport("@shikijs/transformers", JSImport.Namespace)
object Transformers extends js.Object {
  def transformerNotationDiff(): js.Object = js.native
  def transformerNotationHighlight(): js.Object = js.native
}

@main
def main() = {
  val app  = dom.document.getElementById("scalajsapp")
  val code = """
val openEventBus = EventBus[Option[dom.HTMLElement]]()

div(
  // [\!code highlight:2]
  compactSize(true),
  Button(
    _.design := "Emphasized", // [\!code highlight]
    _.onClick.map(_.target).map(Some(_)) --> openEventBus
  )(
    "Open Popover"
  ),
  Popover(
    _.headerText := "Newsletter subscription", // [\!code --]
    _.openerRef(openEventBus.events), // [\!code ++]
  )()
)
    """

  val html = Shiki.codeToHtml(
    code,
    js.Dynamic.literal(
      theme = "vitesse-dark",
      lang = "scala",
      transformers = js.Array(
        Transformers.transformerNotationDiff(),
        Transformers.transformerNotationHighlight()
      )
    )
  )
  html.`then`(html => {
    app.innerHTML = html
  })
}
