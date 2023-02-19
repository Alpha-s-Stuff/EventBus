open module net.minecraftforge.eventbus {
    uses net.minecraftforge.eventbus.IEventBusEngine;

    requires org.objectweb.asm;
    requires org.objectweb.asm.tree;
    requires org.apache.logging.log4j;
    requires static org.jetbrains.annotations;
    requires net.jodah.typetools;

    exports net.minecraftforge.eventbus;
    exports net.minecraftforge.eventbus.api;
    provides net.minecraftforge.eventbus.IEventBusEngine with net.minecraftforge.eventbus.EventBusEngine;
}