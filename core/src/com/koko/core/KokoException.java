package com.koko.core;

/**
 * Created by hundoy on 16-5-16.
 */
public class KokoException extends RuntimeException {

    public static boolean isPrint = true;

    private KokoExType type;
    private String msg;

    private KokoException(KokoExType type, String msg){
        super(msg);
        this.type = type;
        this.msg = msg;
    }

    public static void error(KokoExType type, String msg){
        if (type.equals(KokoExType.FILE_NOT_FOUND)){
            throw new KokoException(KokoExType.FILE_NOT_FOUND, "cannot find the file "+msg);
        }
    }

    public static void warn(KokoExType type, String msg){
        if (!isPrint) return;

        if (type.equals(KokoExType.NO_DEF_CLASS)){
            System.out.println(msg);
        }
    }


}

