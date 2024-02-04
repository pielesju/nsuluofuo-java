package org.cofezuwo.nsuluofuo.graphics;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.video.SDL_Window;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static io.github.libsdl4j.api.Sdl.SDL_Init;
import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;
import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;
import static io.github.libsdl4j.api.render.SDL_RendererFlags.SDL_RENDERER_ACCELERATED;
import static io.github.libsdl4j.api.render.SdlRender.SDL_CreateRenderer;
import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_SHOWN;
import static io.github.libsdl4j.api.video.SdlVideo.SDL_CreateWindow;
import static io.github.libsdl4j.api.video.SdlVideoConst.SDL_WINDOWPOS_CENTERED;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DisplaySDL implements Display {

    @Getter
    private JFrame frame;

    private GLCanvas sdlCanvas;

    private final String title;
    private final int width;
    private final int height;

    public DisplaySDL(ATGSDL a, String title, int width, int height) {


        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    public void createDisplay(ATGSDL a) {
        // Initialize SDL
        int result = SDL_Init(SDL_INIT_EVERYTHING);
        if (result != 0) {
            throw new IllegalStateException("Unable to initialize SDL library (Error code " + result + "): " + SDL_GetError());
        }

        // Create and init the window
        SDL_Window sdlCanvas = SDL_CreateWindow("Demo SDL2", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, width, height, SDL_WINDOW_SHOWN);
        if (sdlCanvas == null) {
            throw new IllegalStateException("Unable to create SDL window: " + SDL_GetError());
        }

        // Create and init the renderer
        SDL_Renderer renderer = SDL_CreateRenderer(sdlCanvas, -1, SDL_RENDERER_ACCELERATED);
        if (renderer == null) {
            throw new IllegalStateException("Unable to create SDL renderer: " + SDL_GetError());
        }

        a.setGraphics(renderer);
    }

    @Override
    public void createDisplay() {

    }

    @Override
    public Canvas getCanvas() {
        return this.sdlCanvas;
    }
}