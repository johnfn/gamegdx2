package game

import com.badlogic.gdx.Game
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.TextureData.TextureDataType
import com.badlogic.gdx.graphics.{Pixmap, TextureData, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer


class Supergame extends Game {
  var batch:SpriteBatch = null
  var img:Texture = null
  var camera:OrthographicCamera = null
  var map:TiledMap = null
  var mapRenderer:TiledMapRenderer = null

  override def create() {
    val width = Gdx.graphics.getWidth()
    val height = Gdx.graphics.getHeight()

    this.batch = new SpriteBatch()
    this.img = new Texture("badlogic.jpg")

    map = new TmxMapLoader().load("map.tmx")
    mapRenderer = new OrthogonalTiledMapRenderer(map)

    camera = new OrthographicCamera()
    camera.setToOrtho(true, width, height)
  }

  override def render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update()
    mapRenderer.setView(camera)
    mapRenderer.render()
  }
}
