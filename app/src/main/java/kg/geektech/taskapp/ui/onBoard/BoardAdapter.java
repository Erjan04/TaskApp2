package kg.geektech.taskapp.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import kg.geektech.taskapp.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private FinishBoard finishBoard;
    private final int[] images = new int[]{R.drawable.news, R.drawable.trophy, R.drawable.enjoy};
    private final String[] titles = new String[]{"Свежие новости", "Лучшее качество", "Наслаждайтесь!"};
    private final String[] destruction = new String[]{"Смотрите новости,а так же выкладывайте и свои!", "Смотрите все лучшие новости,в наилучшем качестве!", "Нажмите на кнопку старт,что бы войти!"};

    public void setFinishBoard(FinishBoard finishBoard) {
        this.finishBoard = finishBoard;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView2;
        private final Button button;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            button = itemView.findViewById(R.id.startBtn);
            button.setOnClickListener(v -> finishBoard.btnClickFinishBoard());
        }

        public void bind(int position) {
            imageView.setImageResource(images[position]);
            textView.setText(titles[position]);
            textView2.setText(destruction[position]);
            if (position == titles.length - 1) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
        }
    }

    interface FinishBoard {
        void btnClickFinishBoard();
    }
}
