package com.canteen.app.activity.client.menu.list;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.activity.client.menu.details.MenuDetailsActivity;
import com.canteen.app.models.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

@Getter
class MenuListsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.menu_name_text_view)
    TextView nameTextView;

    @Setter
    private String id;

    @Setter
    private Menu menu;

    MenuListsViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), MenuDetailsActivity.class);
            intent.putExtra("menu", menu);
            itemView.getContext().startActivity(intent);
        });
    }
}