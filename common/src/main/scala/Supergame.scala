package game

import com.badlogic.gdx.Game
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.TextureData.TextureDataType
import com.badlogic.gdx.graphics.{Pixmap, TextureData, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Supergame extends Game {
  var batch:SpriteBatch = null
  var img:Texture = null

  override def create() {
    this.batch = new SpriteBatch()
    this.img = new Texture("badlogic.jpg")
  }

  override def render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();
      //batch.draw(img, 0, 0);
    batch.end();
  }
}
