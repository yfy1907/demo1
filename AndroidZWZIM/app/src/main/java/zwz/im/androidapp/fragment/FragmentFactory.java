package zwz.im.androidapp.fragment;

import java.util.HashMap;

import zwz.im.androidapp.activity.base.BaseFragment;

public class FragmentFactory {

    private static HashMap<Integer, BaseFragmentNewVocalCards> mFragmentMap = new HashMap<Integer, BaseFragmentNewVocalCards>();

    public static BaseFragmentNewVocalCards createFragment(int pos) {
        // 先从集合中取, 如果没有,才创建对象, 提高性能
        BaseFragmentNewVocalCards fragment = mFragmentMap.get(pos);

        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new TalkFragment();
                    break;
                case 1:
                    fragment = new VoiceFragment();
                    break;
                case 2:
                    fragment = new ChatRoomFragment();
                    break;
                case 3:
                    fragment = new ConnectFragment();
                    break;
                case 4:
                    fragment = new MineFragment();
                    break;

                default:
                    break;
            }

            mFragmentMap.put(pos, fragment);// 将fragment保存在集合中
        }

        return fragment;
    }
}
