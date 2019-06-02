package io.github.zekerzhayard.pressltoshoutgui.asm;

import java.util.Arrays;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class ClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("games.strafekits.pressltoshout.common.ConfigLoader")) {
            ClassNode cn = new ClassNode();
            new ClassReader(basicClass).accept(cn, ClassReader.EXPAND_FRAMES);
            cn.methods.stream()
                    .filter(mn -> mn.name.equals("load") && mn.desc.equals("()V"))
                    .flatMap(mn -> Arrays.stream(mn.instructions.toArray()))
                    .filter(ain -> ain instanceof MethodInsnNode)
                    .map(ain -> (MethodInsnNode) ain)
                    .filter(min -> min.getOpcode() == Opcodes.INVOKEVIRTUAL && min.owner.equals("net/minecraftforge/common/config/Configuration") && min.name.equals("get") && min.desc.equals("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/minecraftforge/common/config/Property;"))
                    .forEach(min -> {
                        min.setOpcode(Opcodes.INVOKESTATIC);
                        min.owner = "PressLToShoutHook";
                        min.name = "setConfig";
                        min.desc = "(Lnet/minecraftforge/common/config/Configuration;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lnet/minecraftforge/common/config/Property;";
                    });
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            cn.accept(cw);
            return cw.toByteArray();
        }
        return basicClass;
    }
}
