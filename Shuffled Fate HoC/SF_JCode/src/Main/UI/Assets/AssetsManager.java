package Main.UI.Assets;

import java.util.*;
import java.io.*;

// AssetsManager формирует "Буфер Текстур", загружает в него текстуры из необходимых txt-файлов и предоставляет к ним доступ по ключам
public class AssetsManager {
    public static final String pathAssetsDir = "D:/Programing/Lab/Game Card/Shuffled Fate HoC/SF_JCode/src/Test/UI/Assets/";

    private final Map<String, String[]> textureMap = new HashMap<>(); // Словарь всех загруженных текстур
    
    public AssetsManager() {
        loadTexturesFrom(pathAssetsDir + "Base");
        loadTexturesFrom(pathAssetsDir + "Castom_lang");
    } // Конструктор - производит подгрузку базовых текстур

    // Геттер Текстуры по ключу
    public String[] getTexture(String id) {
        if (textureMap.containsKey(id)) {
            return textureMap.get(id).clone();
        } else {
            return new String[] {
                    "| ТЕКСТУРА",
                    "| " + id,
                    "| НЕ ЗАГРУЖЕНА" };
        }
    }

    public void loadTexturesFrom(String path) {
        loadTexturesFrom(new File(path));
    }
    public void loadTexturesFrom(File file) {
        if (file.isDirectory())
            loadTexturesFromDirectory(file);
        else
            loadTexturesFromFile(file);
    }

    /// Рекурсивно добавляет в буфер текстур (textureMap) текстуры из всех txt-файлов в переданной директории
    private void loadTexturesFromDirectory(File directory) {
        File[] files = directory.listFiles();

        if (files != null)
            for (File f : files)
                loadTexturesFrom(f);

    }

    /// Добавляет в буфер текстур (textureMap) текстуры из переданного txt-файла
    private void loadTexturesFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            String currentId = null;
            List<String> buffer = new ArrayList<>();

            while ( (line = reader.readLine()) != null ) {

                if (isKey(line)) {

                    // сохранить предыдущую текстуру
                    if (currentId != null) {
                        saveBuffer(currentId, buffer);
                    }

                    currentId = line;
                    buffer = new ArrayList<>();
                    continue;
                }

                if (currentId != null) {
                    buffer.add(line);
                }

            }

            saveBuffer(currentId, buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// Функции класса
    private boolean isKey(String line) {
        return line.startsWith("[") && line.endsWith("]"); }

    private void saveBuffer(String id, List<String> buffer) {
        String[] data = buffer.toArray(new String[0]);
        textureMap.put(id, data);
    }
}


