import javax.swing.*;
import java.awt.*;

public class ChoiceOfResolution {
    public static void main(String[] args) {
        JFrame frame = new JFrame();//создаем форму
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Выбор разрешения");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        String [] selectDim = new String[]{"800x600","1024x768","1200x600","1280x1024","1680x1050","1920x1080"};//создаем массив разрешений для пользовательского диалога
        Object result = new JOptionPane().showInputDialog(frame, "Выберите разрешение", "Выбор разрешения", JOptionPane.QUESTION_MESSAGE,null,selectDim,selectDim[0]);//выводим диалог
        int width= Integer.parseInt(result.toString().substring(0,result.toString().lastIndexOf("x"))); //по результатам диалога задаем размер формы
        int height = Integer.parseInt(result.toString().substring(result.toString().lastIndexOf("x")+1));
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры и местоположение формы
        frame.setVisible(true);//делаем форму видимой
    }
}
