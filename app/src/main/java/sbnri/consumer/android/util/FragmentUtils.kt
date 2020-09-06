package sbnri.consumer.android.util


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import sbnri.consumer.android.R

/**
 * Created by yashThakur on 09/02/17.
 */

class FragmentUtils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    internal class Args(val type: Int, val id: Int, val isHide: Boolean, val isAddStack: Boolean)

    companion object {

        private val TYPE_ADD_FRAGMENT = 0x01
        private val TYPE_REPLACE_FRAGMENT = 0x01 shl 5

        private val ARGS_TYPE = "args_type"
        private val ARGS_ID = "args_id"
        private val ARGS_IS_HIDE = "args_is_hide"
        private val ARGS_IS_ADD_STACK = "args_is_add_stack"

        /**
         * 新增fragment
         *
         * @param fragmentManager fragment管理器
         * @param containerId     布局Id
         * @param fragment        fragment
         * @return fragment
         */
        fun addFragment(fragmentManager: FragmentManager,
                        fragment: Fragment,
                        containerId: Int): Fragment {
            return addFragment(fragmentManager, fragment, containerId, false)
        }

        /**
         * 新增fragment
         *
         * @param fragmentManager fragment管理器
         * @param containerId     布局Id
         * @param fragment        fragment
         * @param isHide          是否显示
         * @param isAddStack      是否入回退栈
         * @return fragment
         */
        @JvmOverloads
        fun addFragment(fragmentManager: FragmentManager,
                        fragment: Fragment,
                        containerId: Int,
                        isHide: Boolean,
                        isAddStack: Boolean = false): Fragment {
            putArgs(fragment, Args(TYPE_ADD_FRAGMENT, containerId, isHide, isAddStack))
            val name = fragment.javaClass.name
            val ft = fragmentManager.beginTransaction()
            ft.add(containerId, fragment, name)
            if (isAddStack) {
                ft.addToBackStack(name)
            }
            ft.commit()
            return fragment
        }

        /**
         * 替换fragment
         *
         * @param srcFragment  源fragment
         * @param destFragment 目标fragment
         * @param isAddStack   是否入回退栈
         * @return `null` 失败
         */
        fun replaceFragment(srcFragment: Fragment,
                            destFragment: Fragment,
                            isAddStack: Boolean): Fragment? {
            if (srcFragment.arguments == null) return null
            val containerId = srcFragment.arguments!!.getInt(ARGS_ID)
            return if (containerId == 0) null else replaceFragment(srcFragment.fragmentManager!!, containerId, destFragment, isAddStack)
        }

        fun replaceFragment(srcFragment: Fragment,
                            destFragment: Fragment,
                            isAddStack: Boolean, tag: String): Fragment? {
            if (srcFragment.arguments == null) return null
            val containerId = srcFragment.arguments!!.getInt(ARGS_ID)
            return if (containerId == 0) null else replaceFragment(srcFragment.fragmentManager!!, containerId, destFragment, isAddStack, tag)
        }

        fun replaceFragment(fragmentManager: FragmentManager,
                            containerId: Int,
                            fragment: Fragment,
                            isAddStack: Boolean, tag: String): Fragment {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            if (isAddStack) {
                ft.setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
                ft.addToBackStack(tag)
                ft.replace(containerId, fragment, tag)
                ft.commit()
                return fragment
            }
            ft.replace(containerId, fragment, tag)
            ft.commit()
            return fragment
        }

        fun replaceFragmentWithoutAnimation(fragmentManager: FragmentManager,
                                            containerId: Int,
                                            fragment: Fragment,
                                            isAddStack: Boolean, tag: String): Fragment {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            if (isAddStack) {
                ft.addToBackStack(tag)
                ft.replace(containerId, fragment, tag)
                ft.commit()
                return fragment
            }
            ft.replace(containerId, fragment, tag)
            ft.commit()
            return fragment
        }

        /**
         * 替换fragment
         *
         * @param fragmentManager fragment管理器
         * @param containerId     布局Id
         * @param fragment        fragment
         * @param isAddStack      是否入回退栈
         * @return fragment
         */
        fun replaceFragment(fragmentManager: FragmentManager,
                            containerId: Int,
                            fragment: Fragment,
                            isAddStack: Boolean): Fragment {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            val name = fragment.javaClass.name
            if (isAddStack) {
                ft.setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
                ft.addToBackStack(name)
                ft.replace(containerId, fragment, name)
                ft.commit()
                return fragment
            }
            ft.replace(containerId, fragment, name)
            ft.commit()
            return fragment
        }

        fun replaceFragmentWithoutAnimation(fragmentManager: FragmentManager,
                                            containerId: Int,
                                            fragment: Fragment,
                                            isAddStack: Boolean): Fragment {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            val name = fragment.javaClass.name
            if (isAddStack) {
                ft.addToBackStack(name)
                ft.replace(containerId, fragment, name)
                ft.commit()
                return fragment
            }
            ft.replace(containerId, fragment, name)
            ft.commit()
            return fragment
        }

        fun replaceFragment(fragmentManager: FragmentManager,
                            containerId: Int,
                            fragment: Fragment,
                            isAddStack: Boolean, enterTransition: Int, exitTransition: Int): Fragment {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            val name = fragment.javaClass.name
            if (isAddStack) {
                ft.setCustomAnimations(enterTransition, 0, 0, enterTransition)
                ft.addToBackStack(name)
                ft.replace(containerId, fragment, name)
                ft.commit()
                return fragment
            }
            ft.replace(containerId, fragment, name)
            ft.commit()
            return fragment
        }

        fun replaceFromRight(fragmentManager: FragmentManager,
                             containerId: Int,
                             fragment: Fragment,
                             isAddStack: Boolean) {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            val name = fragment.javaClass.name
            if (isAddStack) {
                ft.setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
                ft.addToBackStack(name)
                ft.replace(containerId, fragment, name)
                ft.commit()
                return
            }
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            ft.replace(containerId, fragment, name)
            ft.commit()
        }

        fun replaceFromLeft(fragmentManager: FragmentManager,
                            containerId: Int,
                            fragment: Fragment,
                            isAddStack: Boolean) {
            putArgs(fragment, Args(TYPE_REPLACE_FRAGMENT, containerId, false, isAddStack))
            val ft = fragmentManager.beginTransaction()
            val name = fragment.javaClass.name
            if (isAddStack) {
                ft.setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
                ft.addToBackStack(name)
                ft.replace(containerId, fragment, name)
                ft.commit()
                return
            }
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
            ft.replace(containerId, fragment, name)
            ft.commit()
        }

        /**
         * 传参
         *
         * @param fragment fragment
         * @param args     参数
         */
        private fun putArgs(fragment: Fragment, args: Args) {
            var bundle = fragment.arguments
            if (bundle == null) {
                bundle = Bundle()
                fragment.arguments = bundle
            }
            bundle.putInt(ARGS_TYPE, args.type)
            bundle.putInt(ARGS_ID, args.id)
            bundle.putBoolean(ARGS_IS_HIDE, args.isHide)
            bundle.putBoolean(ARGS_IS_ADD_STACK, args.isAddStack)
        }

        /**
         * 获取参数
         *
         * @param fragment fragment
         */
        private fun getArgs(fragment: Fragment): Args? {
            val bundle = fragment.arguments
            return if (bundle == null || bundle.getInt(ARGS_TYPE) == 0) null else Args(bundle.getInt(ARGS_TYPE), bundle.getInt(ARGS_ID), bundle.getBoolean(ARGS_IS_HIDE), bundle.getBoolean(ARGS_IS_ADD_STACK))
        }
    }
}
/**
 * 新增fragment
 *
 * @param fragmentManager fragment管理器
 * @param containerId     布局Id
 * @param fragment        fragment
 * @param isHide          是否显示
 * @return fragment
 */