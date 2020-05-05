package violentapplications.guitest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2017-09-23.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;

    public Adapter(Context context) {
        this.context = context;
    }

    public void setSoundList(List<Sound> list) {
        soundList.clear();
        soundList.addAll(list);
        notifyDataSetChanged();
    }

    List<Sound> soundList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View soundItemView = LayoutInflater.from(context).inflate(R.layout.sound_item, parent, false);
        return new ViewHolder(soundItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Sound sound = soundList.get(position);
        holder.soundButton.setText(sound.getName());
        if (onItemClickListener != null) {
            holder.soundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(sound);
                }
            });
            holder.menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onContextMenu(sound);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.soundButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(sound);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private Button soundButton;
        private ImageButton menu;

        public ViewHolder(View itemView) {
            super(itemView);
            soundButton = (Button) itemView.findViewById(R.id.sound_button);
            menu = (ImageButton) itemView.findViewById(R.id.menu_button);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Sound item);
        void onContextMenu(Sound item);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Sound item);
    }
}

