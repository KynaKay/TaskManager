package sg.edu.rp.a16046508.taskmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    ArrayList<Task> al;
    Context context;
    int resource;

    public TaskArrayAdapter(@NonNull Context context, int resource,
                            ArrayList<Task> objects) {
        super(context, resource);
        this.context = context;
        this.al = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent,
                false);

        return rowView;
    }
}
