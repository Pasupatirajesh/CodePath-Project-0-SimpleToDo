package testapp.codepath.com.simpletodo;

/**
 * Created by SSubra27 on 6/6/16.
 */
public class ToDo {

    private String ListArrayIndex;
    private String ListArrayContent;

    public ToDo(String index, String content)
    {
        ListArrayIndex = index;
        ListArrayContent = content;

    }
    public String getListArrayIndex() {
        return ListArrayIndex;
    }

    public void setListArrayIndex(String listArrayIndex) {
        ListArrayIndex = listArrayIndex;
    }

    public String getListArrayContent() {
        return ListArrayContent;
    }

    public void setListArrayContent(String listArrayContent) {
        ListArrayContent = listArrayContent;
    }
}
