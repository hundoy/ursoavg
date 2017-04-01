package com.koko.lines;

import com.koko.bean.KokoLine;
import com.koko.helper.Aktoro;
import com.zohar.common.util.RegExpUtil;

import java.util.List;

import static com.zohar.common.util.ToolUtil.isnoe;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLText extends KokoLine {
    private int textIndex = 0;
    private String cmdName;
    private String cmdParam;

    @Override
    public void process() {
        // XXX display text...
        System.out.println(oriScript);
//        g.layer.getFocusLayer().addCurText(oriScript);
//        g.logic.say();
        textIndex = -1;
        nextWord();
    }

    public void nextWord(){
        textIndex++;
        Aktoro aktoro = sayer.getAktoro();
        char curWord = oriScript.charAt(textIndex);

        boolean isNormalWord = true;

        if (curWord=='\\') {
            // word command. \c[param]  c is the command name, only one character.
            List<String> command = RegExpUtil.regFindFirstByAllGroup("\\\\(\\S)(\\[([^\\]]*)\\])?", oriScript.substring(textIndex));
            if (isnoe(command)){
                cmdName = command.get(0);
                cmdParam = command.size()>=3 ? command.get(2) : null;
                if (cmdName.equals("w")) {
                    // wait some seconds. param = wait second
                    float waitSec = cmdParam!=null ? Float.parseFloat(cmdParam) : 0.5f;
                    sayer.waitTime(waitSec);
                    isNormalWord = false;
                } else if (cmdName.equals("l")) {
                    // wait click. no param
                    sayer.waitClick();
                    isNormalWord = false;
                } else if (cmdName.equals("e")) {
                    // clear dialog
                } else if (cmdName.equals("p")){
                    // wait click and clear dialog

                }
            }
        }

        if (isNormalWord){
            // normal word
            aktoro.talkOneWord(String.valueOf(oriScript.charAt(textIndex)));
            sayer.waitTime(aktoro.getWordInterSec());
        }
    }

    @Override
    public boolean isReachEnd(){
        return textIndex > oriScript.length()-1;
    }

    @Override
    public void afterWait() {
//        g.layer.getFocusLayer().afterWait();
        if (isReachEnd()){
            // reach line end. go next line
            sayer.nextLine();
        } else{
            // NOT reach line end. paint next word.
            nextWord();
        }
    }
}
