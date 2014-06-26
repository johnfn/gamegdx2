package game

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.{InputProcessor, Game, ApplicationAdapter, Gdx}
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.TextureData.TextureDataType
import com.badlogic.gdx.graphics.{Pixmap, TextureData, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.{MapLayers, MapLayer}
import com.badlogic.gdx.maps.tiled.{TiledMapTileLayer, TiledMap, TiledMapRenderer, TmxMapLoader}
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.{Intersector, Rectangle}
import scala.collection.JavaConverters._

class Entity(textureFile:String) extends Rectangle(0, 0, 25, 25) {
  var texture:Texture = new Texture(textureFile)
}

class Player extends Entity("player.png") {
  def render(batch:SpriteBatch) {
    batch.draw(texture, this.x, this.y)
  }

  def update() {
    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      this.x += 5
    }

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      this.x -= 5
    }

    if (Gdx.input.isKeyPressed(Keys.UP)) {
      this.y += 5
    }

    if (Gdx.input.isKeyPressed(Keys.DOWN)) {
      this.y -= 5
    }
  }
}

class Tilemap(mapName:String) {
  var map:TiledMap = new TmxMapLoader().load(mapName)
  var mapRenderer:TiledMapRenderer = new OrthogonalTiledMapRenderer(map)

  def layerVector():Vector[MapLayer] = map.getLayers().iterator().asScala.to[Vector]
  val layers:Map[String, MapLayer] = layerVector().map((layer) => (layer.getName(), layer)).toMap
  val objects:Map[String, Vector[Rectangle]] = layers
    .mapValues( (l) => (l.getObjects().iterator().asScala.to[Vector]))
    .mapValues( (objectlist) => objectlist.map((x) => x.asInstanceOf[RectangleMapObject].getRectangle()))

  def render(camera:OrthographicCamera) {
    mapRenderer.setView(camera)
    mapRenderer.render()
  }

  def collideWith(entity:Entity):Boolean = {
    val allObjects = objects.values.flatten

    if (allObjects.toVector.length > 0) println(allObjects.toVector(0))

    allObjects.map((rect) => Intersector.overlaps(rect, entity)).toVector.contains(true)
  }
}

class Supergame extends Game {
  var batch:SpriteBatch = null
  var img:Texture = null
  var camera:OrthographicCamera = null
  var player:Player = null
  var tilemap:Tilemap = null

  override def create() {
    val width:Float = Gdx.graphics.getWidth.asInstanceOf[Float]
    val height:Float = Gdx.graphics.getHeight.asInstanceOf[Float]

    this.batch = new SpriteBatch()
    this.img = new Texture("badlogic.jpg")

    camera = new OrthographicCamera()
    camera.setToOrtho(false, width, height)

    this.tilemap = new Tilemap("map.tmx")
    this.player = new Player()
  }

  override def render() {
    Gdx.gl.glClearColor(1, 1, 1, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    player.update()

    batch.setProjectionMatrix(camera.combined)
    batch.begin()
    player.render(batch)
    batch.end()

    camera.update()
    tilemap.render(camera)

    println(tilemap.collideWith(player))
  }
}

object Supergame {
  def __rebelReload() = {
    println("zomg, reload.")
  }
}
