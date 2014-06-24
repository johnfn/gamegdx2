package game

import com.badlogic.gdx.backends.lwjgl._

object Main extends App {
    val cfg = new LwjglApplicationConfiguration
    cfg.title = "supergame"
    cfg.height = 480
    cfg.width = 320
    cfg.useGL30 = true
    cfg.forceExit = false

    new LwjglApplication(new Supergame, cfg)
}
