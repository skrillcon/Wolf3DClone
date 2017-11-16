package com.base.engine;

import java.util.ArrayList;

public class Game {

    Bitmap level;
    Shader shader;
    Material material;
    Mesh mesh;
    Transform transform;

    private static final float SPOT_WIDTH = 1f;
    private static final float SPOT_LENGTH = 1f;
    private static final float SPOT_HEIGHT = 1f;

    public Game(){
        level = new Bitmap("TestLevel.png").flipY();
        shader = BasicShader.getInstance();
        material = new Material(new Texture("Marble.jpg"));

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for(int i = 0; i < level.getWidth(); i++){
            for(int j = 0; j < level.getHeight(); j++){
                // If it's a wall
                if((level.getPixel(i,j) & 0xFFFFFF) == 0){
                    continue;
                }
                float XHigher = 1;
                float XLower = 0;
                float YHigher = 1;
                float YLower = 0;

                // Generate Floor
                indices.add(vertices.size() + 2);
                indices.add(vertices.size() + 1);
                indices.add(vertices.size() + 0);
                indices.add(vertices.size() + 3);
                indices.add(vertices.size() + 2);
                indices.add(vertices.size() + 0);

                vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(XLower,YLower)));
                vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(XHigher,YLower)));
                vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,(j + 1) * SPOT_LENGTH), new Vector2f(XHigher,YHigher)));
                vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,(j + 1) * SPOT_LENGTH), new Vector2f(XLower,YHigher)));

                // Generate Ceiling
                indices.add(vertices.size() + 0);
                indices.add(vertices.size() + 1);
                indices.add(vertices.size() + 2);
                indices.add(vertices.size() + 0);
                indices.add(vertices.size() + 2);
                indices.add(vertices.size() + 3);

                vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(XLower,YLower)));
                vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(XHigher,YLower)));
                vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,(j + 1) * SPOT_LENGTH), new Vector2f(XHigher,YHigher)));
                vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,(j + 1) * SPOT_LENGTH), new Vector2f(XLower,YHigher)));

                // Generate Walls
                if((level.getPixel(i,j - 1) & 0xFFFFFF) == 0){
                    indices.add(vertices.size() + 0);
                    indices.add(vertices.size() + 1);
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 0);
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 3);

                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(XLower,YLower)));
                    vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(XHigher,YLower)));
                    vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,j * SPOT_LENGTH), new Vector2f(XHigher,YHigher)));
                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,j  * SPOT_LENGTH), new Vector2f(XLower,YHigher)));
                }
                if((level.getPixel(i,j + 1) & 0xFFFFFF) == 0){
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 1);
                    indices.add(vertices.size() + 0);
                    indices.add(vertices.size() + 3);
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 0);

                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(XLower,YLower)));
                    vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(XHigher,YLower)));
                    vertices.add(new Vertex(new Vector3f((i + 1) * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(XHigher,YHigher)));
                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(XLower,YHigher)));
                }
                if((level.getPixel(i - 1,j) & 0xFFFFFF) == 0){
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 1);
                    indices.add(vertices.size() + 0);
                    indices.add(vertices.size() + 3);
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 0);

                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(XLower,YLower)));
                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(XHigher,YLower)));
                    vertices.add(new Vertex(new Vector3f(i  * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(XHigher,YHigher)));
                    vertices.add(new Vertex(new Vector3f(i * SPOT_WIDTH,SPOT_HEIGHT,j  * SPOT_LENGTH), new Vector2f(XLower,YHigher)));
                }

                if((level.getPixel(i + 1,j) & 0xFFFFFF) == 0){
                    indices.add(vertices.size() + 0);
                    indices.add(vertices.size() + 1);
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 0);
                    indices.add(vertices.size() + 2);
                    indices.add(vertices.size() + 3);

                    vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,0,j * SPOT_LENGTH), new Vector2f(XLower,YLower)));
                    vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,0,(j+1) * SPOT_LENGTH), new Vector2f(XHigher,YLower)));
                    vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,SPOT_HEIGHT,(j+1) * SPOT_LENGTH), new Vector2f(XHigher,YHigher)));
                    vertices.add(new Vertex(new Vector3f((i+1) * SPOT_WIDTH,SPOT_HEIGHT,j  * SPOT_LENGTH), new Vector2f(XLower,YHigher)));
                }
            }
        }

        Vertex[] vertArray = new Vertex[vertices.size()];
        Integer[] intArray = new Integer[indices.size()];

        vertices.toArray(vertArray);
        indices.toArray(intArray);

        mesh = new Mesh(vertArray, Util.toIntArray(intArray));
        transform = new Transform();
        Transform.setCamera(new Camera());
        transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
    }

    public void start(){

    }

    public void input(){
        Transform.getCamera().input();
    }

    public void update(){

    }

    public void render(){
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
        shader.unbind();
    }
}
