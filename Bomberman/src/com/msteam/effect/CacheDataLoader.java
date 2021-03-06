package com.msteam.effect;


import javafx.fxml.LoadException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * tránh tạo instance khác nó chỉ dùng 1 instance
 */

public class CacheDataLoader {

    private static CacheDataLoader instance;

    private String framefile = "Bomberman\\data\\frame.txt";
    private String animationfile = "Bomberman\\data\\animation.txt";
    private String levelmapfile = "Bomberman\\data\\Level1.txt";

    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;
    /**
     * map: bản đồ của level
     * width: chiều rộng
     * height: chiều cao
     */
    private String[][] map;
    protected int _width, _height; // default values just for testing
    protected int _level;

    public int get_width(){

        return instance._width;
    }

    public int get_height(){

        return instance._height;
    }

    private CacheDataLoader(){

        frameImages = new Hashtable<String, FrameImage>();
        animations = new Hashtable<String, Animation>();

        //_height = 20;
    }

    public static CacheDataLoader getInstance(){

        if (instance == null){

            instance = new CacheDataLoader();
        }
        return instance;
    }

    public FrameImage getFrameImage(String name){

        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;
    }

    public Animation getAnimation(String name){

        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

    public String[][] getMap(){

        return instance.map;
    }

    /**
     * Load dữ liệu map từ file Level1.txt
     * @throws IOException
     */
    public void LoadMap() throws IOException {
        try {
            FileReader fr = new FileReader(levelmapfile);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            line = br.readLine();

            StringTokenizer tokens = new StringTokenizer(line);

            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            instance.map = new String[get_height()][get_width()];
            for(int i = 0;i < _height;i++){
                line = br.readLine();
                for (int j = 0; j<_width;j++){
                    instance.map[i][j] = String.valueOf(line.charAt(j));
                }
            }
            br.close();
        } catch (IOException e) {
            throw new IOException("Error loading level " + levelmapfile, e);
        }

    }

    public void LoadData() throws IOException {

        LoadFrame();
        LoadAnimation();
        LoadMap();
    }

    public void LoadFrame() throws IOException{

        frameImages = new Hashtable<String, FrameImage>();

        FileReader fr = new FileReader(new File(framefile));
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if (br.readLine() == null){

            System.out.println("No data input");
            throw new IOException();
        }
        else{

            fr = new FileReader(framefile);
            br = new BufferedReader(fr);

            while((line = br.readLine()).equals("")){};

            int n = Integer.parseInt(line);

            for (int i = 0; i < n; i++){

                FrameImage frame = new FrameImage();

                while((line = br.readLine()).equals("")){};
                frame.setName(line);

                while((line = br.readLine()).equals("")){};
                String[] str = line.split(" ");
                String path = str[1];

                while((line = br.readLine()).equals("")){};
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals("")){};
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals("")){};
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals("")){};
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                BufferedImage imageData = ImageIO.read(new FileImageInputStream(new File(path)));
                BufferedImage image = imageData.getSubimage(x, y, w, h);
                frame.setImage(image);

                instance.frameImages.put(frame.getName(), frame);
            }
        }

        br.close();
    }

    public void LoadAnimation() throws IOException{

        animations = new Hashtable<String, Animation>();

        FileReader fr = new FileReader(animationfile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if (br.readLine() == null){

            System.out.println("No data input");
            throw  new IOException();
        }
        else {

            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);

            while((line = br.readLine()).equals("")){};

            int n = Integer.parseInt(line);

            for (int i = 0; i < n; i++){

                Animation animation = new Animation();

                while((line = br.readLine()).equals("")){};
                animation.setName(line);

                while((line = br.readLine()).equals("")){};
                String[] str = line.split(" ");

                for (int j = 0; j < str.length; j+=2){

                    animation.add(instance.getFrameImage(str[j]),Double.parseDouble(str[j+1]));
                }
                instance.animations.put(animation.getName(), animation);
            }
        }

        br.close();
    }
}
