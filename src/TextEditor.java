import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor extends JFrame {

    private JPanel panel;
    private JTextPane text;
    private JButton buttonOpen;
    private JButton buttonSave;
    private JButton buttonSaveAs;
    private JLabel labelFile;
    public File file;

    public TextEditor() {
        this.getContentPane().add(panel);//добавляем панель на форму

        buttonOpen.addActionListener(e -> {//открыть файл
            if (dialog(FileDialog.LOAD,"Открыть текстовый файл")){//если диалог прошел успешно
                StringBuilder s = new StringBuilder();//создаем строку
                try (FileReader reader = new FileReader(file)) {//считываем все в строку ридером
                    int c;
                    while ((c = reader.read()) != -1) //пока есть символы
                        s.append ((char)c);//добавляем из в строку
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                text.setText(s.toString());//записываем строку в редактор
                labelFile.setText(file.getName());//
            }
        });

        buttonSave.addActionListener(e -> {//сохранить файл
            if (file!=null) save();//если файл был открыт то сохраняем в него
            else saveNew();//сохраняем как новый файл
        });

        buttonSaveAs.addActionListener(e-> {//сохранить файл как
            saveNew();//сохраняем как новый файл
        });
    }

    private void saveNew() {
        if (text.getText().length() > 0)//если файла не было но есть текст, то открываем диалог
            if (dialog(FileDialog.SAVE,"Сохранить текстовый файл")) {//если диалог прошел успешно
                save();
                labelFile.setText(file.getName());//
            }
    }

    private void save() {//метод записи файла
        try {
            FileWriter writer = new FileWriter(file);//создаем врайтер
            writer.write(text.getText());//пишем в файлё
            writer.flush();//сохраняем
            writer.close();//закрываем врайтер
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean dialog(int dialog, String title){//метод диалога открытия или сохранения файла
        FileDialog fileDialog = new FileDialog(new Frame(), title, dialog);//создаем диалог
        fileDialog.setFile("*.txt");//устанавливаем фильтр
        fileDialog.setVisible(true);//показываем диалог
        File[] files =fileDialog.getFiles();//получаем массив файлов от диалога (а может ничего)
        if (files.length > 0) {//проверяем, возвращено ли что-то
            file = files[0];
            return true;
        } else return false;
    }

    public static void main(String[] args) {
        TextEditor t = new TextEditor();
    }
}
