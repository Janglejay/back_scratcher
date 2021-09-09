package com.janglejay;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Main {
    public static void main(String[] args) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("我马上就要去剪切板了"), null);
    }
}
