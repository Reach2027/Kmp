# 快速 KMP 模块

由于 Android Studio 还不支持创建 KMP 模块，且 Android Studio 创建 Android 模块会引入一些不必要的引用，
所以创建一个这个目录，方便快速创建 KMP 模块

* 复制这个目录到需要创建模块的地方（feature、feature-data、ui-core、ui-base、data-core、data-base）
* 改名为目标模块，并修改模块包名
* 在 setting.gradle.kts 里增加新模块
* 修改新模块的 gradle 配置
