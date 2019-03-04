package zwz.im.androidapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import zwz.im.androidapp.R;
import zwz.im.androidapp.adapter.MsgAdapter;
import zwz.im.androidapp.adapter.RecyclerAdapter;
import zwz.im.androidapp.model.Fruit;
import zwz.im.androidapp.model.Msg;


public class ChatRoomFragment extends BaseFragmentHome implements View.OnClickListener {

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoaded;

    private ImageView back, iv_force, iv_finish, kb_voice, kb_video, kb_pic, kb_expression, kb_camera, speak_voice;
    private List<Msg> msgList = new ArrayList<Msg>();
    private EditText inputText, et_reason;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private RecyclerView rv_photo;
    private List<Fruit> fruitList = new ArrayList<>();
    private TextView add_photo, tv_album, reason_three, reason_two, reason_one, tv_reason;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("michael-onCreateView", "心室");
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.heart_chat, container, false);
            initMsgs();
            initView();
            isPrepared = true;
            // 实现懒加载
            lazyLoad();
        }
        //缓存的mView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        back = mView.findViewById(R.id.back);
        iv_force = mView.findViewById(R.id.iv_force);
        iv_finish = mView.findViewById(R.id.iv_finish);
        inputText = mView.findViewById(R.id.input_text);
        kb_voice = mView.findViewById(R.id.kb_voice);
        kb_video = mView.findViewById(R.id.kb_video);
        kb_pic = mView.findViewById(R.id.kb_pic);
        kb_expression = mView.findViewById(R.id.kb_expression);
        kb_camera = mView.findViewById(R.id.kb_camera);
        msgRecyclerView = mView.findViewById(R.id.msg_recycler_view);
        back.setOnClickListener(this);
        iv_force.setOnClickListener(this);
        iv_finish.setOnClickListener(this);
        kb_voice.setOnClickListener(this);
        kb_video.setOnClickListener(this);
        kb_pic.setOnClickListener(this);
        kb_expression.setOnClickListener(this);
        kb_camera.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

    }

    private void initMsgs() {
        Msg msg1 = new Msg("最近的心情很不好", Msg.TYPE_RECEIVED, 0);
        msgList.add(msg1);
        Msg msg2 = new Msg("发生什么事了呢", Msg.TYPE_SENT, 0);
        msgList.add(msg2);
        Msg msg13 = new Msg("好好调整一下心态", Msg.TYPE_RECEIVED, R.drawable.video);
        msgList.add(msg13);
        Msg msg3 = new Msg("好好调整一下心态", Msg.TYPE_RECEIVED, 0);
        msgList.add(msg3);
        Msg msg4 = new Msg("一切都会过去的", Msg.TYPE_RECEIVED, 0);
        msgList.add(msg4);
        Msg msg5 = new Msg("风雨总会见彩虹", Msg.TYPE_RECEIVED, 0);
        msgList.add(msg5);
        Msg msg6 = new Msg("好的呢", Msg.TYPE_SENT, 0);
        msgList.add(msg6);
        Msg msg7 = new Msg("我会好起来的", Msg.TYPE_SENT, 0);
        msgList.add(msg7);
        Msg msg8 = new Msg("放心吧", Msg.TYPE_SENT, 0);
        msgList.add(msg8);
        Msg msg9 = new Msg("君不见，黄河之水天上来，奔流到海不复回。君不见，高堂明镜悲白发，朝如青丝暮成雪。人生得意须尽欢，莫使金樽空对月。天生我才必有用，千金散尽还复来。烹羊宰牛且为乐，会须一饮三百杯。岑夫子，丹丘生，将进酒，杯莫停。与君歌一曲，请君为我倾耳听。钟鼓馔玉不足贵，但愿长醉不复醒。古来圣贤皆寂寞，唯有饮者留其句。陈王昔日宴平乐，斗酒十千恣欢谑。主人何为言少钱，径须沽取对君酌。五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。", Msg.TYPE_SENT, 0);
        msgList.add(msg9);
        Msg msg10 = new Msg("白酒新熟山中归，黄鸡啄黍秋正肥。呼童烹鸡酌白酒，儿女嘻笑牵人衣。高歌取醉欲自慰，起舞落日争光辉。游说万岁苦不早，著鞭跨马涉远道。会稽愚妇轻买臣，余亦辞家西入秦。仰天大笑出门去，我辈岂是蓬蒿人。", Msg.TYPE_RECEIVED, 0);
        msgList.add(msg10);
        Msg msg11 = new Msg("大鹏一日同风起，扶摇直上九万里。假令风歇时不来，犹能簸却沧溟水时人见我恒殊调，闻余大言皆冷笑。宣父犹能畏后生，丈夫未可轻年少。", Msg.TYPE_SENT, 0);
        msgList.add(msg11);
        Msg msg12 = new Msg("我本楚狂人，凤歌笑孔丘。手持绿玉杖，朝别黄鹤楼。五岳寻仙不辞远，一生好入名山游。庐山秀出南斗傍，屏风九叠云锦张，影落明湖青黛光。金阙前开二峰长，银河倒挂三石梁。香炉瀑布遥相望，回崖沓嶂凌苍苍。翠影红霞映朝日，鸟飞不到吴天长。登高壮观天地间，大江茫茫去不还。黄云万里动风色，白波九道流雪山。好为庐山谣，兴因庐山发。闲窥石镜清我心，谢公行处苍苔没。早服还丹无世情，琴心三叠道初成。遥见仙人彩云里，手把芙蓉朝玉京。先期汗漫九垓上，愿接卢敖游太清。", Msg.TYPE_RECEIVED, 0);
        msgList.add(msg12);
    }

    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoaded) {
            return;
        }
        //填充各控件的数据
        mHasLoaded = true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
//                String content = inputText.getText().toString();
//                if (!"".equals(content)) {
//                    Msg msg = new Msg(content, Msg.TYPE_SENT, 0);
//                    msgList.add(msg);
//                    adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新ListView中的显示
//                    msgRecyclerView.scrollToPosition(msgList.size() - 1); // 将ListView定位到最后一行
//                    inputText.setText(""); // 清空输入框中的内容
//                }
                showDialog("finish");
                break;

            case R.id.back:
                break;

            case R.id.iv_force:
                showDialog("force");
                break;

            case R.id.kb_video:
                break;

            case R.id.kb_pic:
                showBottomSheetDialog();
                break;

            case R.id.kb_camera:

                break;
            case R.id.kb_expression:
                showDialog2();
                break;
            case R.id.kb_voice:
                showBottomDialog();
                break;
            case R.id.tv_album:
                //showBottomDialog();
                break;
            case R.id.add_photo:
                //showBottomDialog();
                break;
        }
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(getContext(), R.layout.voice_popup, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.tv_change_voice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        speak_voice = dialog.findViewById(R.id.speak_voice);
        speak_voice.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//                Glide.with(getContext()).load(R.drawable.speak_voice).apply(options).into(speak_voice);
                return false;
            }
        });
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.photo_bottom);
        //给布局设置透明背景色
        bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();

        initImages();
        rv_photo = bottomSheetDialog.findViewById(R.id.rv_photo);
        tv_album = bottomSheetDialog.findViewById(R.id.tv_album);
        add_photo = bottomSheetDialog.findViewById(R.id.add_photo);
        assert add_photo != null;
        add_photo.setOnClickListener(this);
        tv_album.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_photo.setLayoutManager(linearLayoutManager);
        rv_photo.setAdapter(new RecyclerAdapter(getContext(), fruitList));
    }

    private void showDialog(String type) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.finish_bottom);
        //给布局设置透明背景色
        bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();

        et_reason = bottomSheetDialog.findViewById(R.id.et_reason);
        tv_reason = bottomSheetDialog.findViewById(R.id.tv_reason);
        reason_three = bottomSheetDialog.findViewById(R.id.reason_three);
        reason_two = bottomSheetDialog.findViewById(R.id.reason_two);
        reason_one = bottomSheetDialog.findViewById(R.id.reason_one);
        if (type.equals("force")) {
            tv_reason.setText("请您选择踢人的原因：");
            reason_one.setText("和他聊天他不能解决我的问题");
            reason_two.setText("和他不是一类人，话不投机");
            reason_three.setText("和他聊天我受到了骚扰");
        }
    }

    private void showDialog2() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.expression);
        //给布局设置透明背景色
        bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();

        et_reason = bottomSheetDialog.findViewById(R.id.et_reason);
    }

    private void initImages() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.image_three, "chat",null);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.image_one, "chat",null);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.image_three, "chat",null);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.image_two, "chat",null);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.image_four, "chat",null);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.image_one, "chat",null);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.image_three, "chat",null);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.image_two, "chat",null);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.image_four, "chat",null);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.image_one, "chat",null);
            fruitList.add(mango);
        }
    }


}
