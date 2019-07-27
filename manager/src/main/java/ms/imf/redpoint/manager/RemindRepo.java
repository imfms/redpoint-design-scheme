package ms.imf.redpoint.manager;

import java.util.Collection;
import java.util.Map;

import ms.imf.redpoint.entity.NodePath;

/**
 * 提醒数据仓库，提供提醒数据的读写存储功能
 *
 * @param <RemindType> 支持的提醒数据类型
 *
 * @author f_ms
 * @date 2019/3/25
 */
public interface RemindRepo<RemindType extends Remind> {

    /**
     * 获取所有提醒数据
     *
     * @return 提醒数据
     */
    Collection<? extends RemindType> getAllReminds();

    /**
     * 获取完全匹配指定节点路径的提醒数据
     *
     * @param nodePaths 用于匹配的节点路径集
     * @return 指定节点路径集合匹配到的提醒数据
     */
    Map<NodePath, ? extends Collection<? extends RemindType>> getMatchReminds(Collection<NodePath> nodePaths);

    /**
     * 获取匹配指定节点路径及其子路径的提醒数据
     *
     * @param nodePaths 用于匹配的节点路径集
     * @return 指定路径集合匹配到的提醒数据
     */
    Map<NodePath, ? extends Collection<? extends RemindType>> getMatchSubReminds(Collection<NodePath> nodePaths);

    /**
     * 移除指定提醒数据集
     *
     * @param reminds 要移除的提醒数据集
     */
    void removeReminds(Iterable<? extends RemindType> reminds);

    /**
     * 移除完全匹配指定节点路径的提醒数据集
     *
     * @param nodePaths 用于匹配节点路径集
     * @return 被移除提醒的数量
     */
    long removeMatchReminds(Collection<NodePath> nodePaths);

    /**
     * 移除匹配指定节点路径及子路径的提醒数据集
     *
     * @param nodePaths 用于匹配的节点路径集
     * @return 被移除提醒的数量
     */
    long removeMatchSubReminds(Collection<NodePath> nodePaths);

    /**
     * 移除所有提醒
     *
     * @return 移除提醒的数量
     */
    long removeAllReminds();

    /**
     * 设置提醒数据改变监听器
     *
     * @param listener 要设置的提醒数据改变监听器
     */
    void setRemindDataChangedListener(RemindDataChangedListener<? super RemindType> listener);
}
