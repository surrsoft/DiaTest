package ru.surrsoft.baaz.widgets.cards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.R;


/**
 * Простая карточка с двумя TextView и кнопкой popup menu
 * Элементы: _title, _comment, _btnopt
 * <p/>
 * [[w194w]]
 */
public class N2194_Card extends RelativeLayout {

    public N2194_Card_Agent _agent;
    public TextView _title;
    public TextView _comment;
    public long _id;
    private TextView _btnopt;

    public interface N2194Callback {
        /**
         * Вызывается при нажатии на тело текущего элемента
         */
        void callbackPress(N2194_Card card);

        /**
         * Вызывается при выборе "удалить" в _btnopt
         * @param card
         */
        void callbackDelete(N2194_Card card);

    }

    public interface N2194_Card_Agent {
        String getTitleText(N2194_Card card);
        String getCommText(N2194_Card card);
        N2194Callback getCallback();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.n2194_lay, this, true); //layout
        _title = (TextView) findViewById(R.id.n2194_title);
        _comment = (TextView) findViewById(R.id.n2194_comment);
        _btnopt = (TextView) findViewById(R.id.n2194_btnopt);

        final PopupMenu popMenu = new PopupMenu(context, _btnopt);
        popMenu.inflate(R.menu.n2194btnopt_popmenu);
        popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.n2194_btnopt_menuelem_delete){
                    if(_agent!=null){
                        _agent.getCallback().callbackDelete(N2194_Card.this);
                    }
                }
                return true;
            }
        });

        _btnopt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popMenu.show();
            }
        });
    }

    public void yCommit() {
        if (_agent != null) {
            _title.setText(_agent.getTitleText(N2194_Card.this));
            _comment.setText(_agent.getCommText(N2194_Card.this));

            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    _agent.getCallback().callbackPress(N2194_Card.this);
                }
            });
        }
    }


    //1 //constructors
    public N2194_Card(Context context) {
        super(context);
        init(context);
    }

    public N2194_Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2194_Card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors


}
