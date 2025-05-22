import "./style.css";
import { codeToHtml } from "shiki";
import * as Transformers from "@shikijs/transformers";

const code = `
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
`;

const html = codeToHtml(code, {
  theme: "vitesse-dark",
  lang: "scala",
  transformers: [
    Transformers.transformerNotationDiff(),
    Transformers.transformerNotationHighlight(),
  ],
});

html.then((result) => {
  document.querySelector("#app").innerHTML = result;
});
