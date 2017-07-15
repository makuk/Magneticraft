package com.cout970.magneticraft.computer

import com.cout970.magneticraft.MOD_ID
import com.cout970.magneticraft.api.core.ITileRef
import com.cout970.magneticraft.misc.network.IBD
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*


/**
 * Created by cout970 on 2017/06/23.
 */

private var timer = 0L
private var lastTick = 0

fun main(args: Array<String>) {

    val monitor = DeviceMonitor(FakeRef)

    val cpu = CPU_MIPS()
    val memory = RAM(0xFFFF + 1, false)
    val rom = ROM("assets/$MOD_ID/cpu/bios.bin")
    val bus = Bus(memory, mutableMapOf())
    val motherboard = Motherboard(cpu, memory, rom, bus)
    val mbDevice = DeviceMotherboard(FakeRef, motherboard)

    bus.devices.put(0xFF, mbDevice)
    bus.devices.put(0x00, monitor)

//     bios string print
//    print("\n\n\n\n\n")
//    println(rom.bios.reader(Charsets.US_ASCII).readText())
//    print("\n\n\n\n\n")
//
////     bios instruction decompiler
//    rom.bios.buffered().iterator().apply {
//        var pc = 0
//        while (hasNext()) {
//            var tmp = 0
//            tmp = tmp.splitSet(0, nextByte())
//            tmp = tmp.splitSet(1, nextByte())
//            tmp = tmp.splitSet(2, nextByte())
//            tmp = tmp.splitSet(3, nextByte())
//            println("0x%08x  ".format(pc) + CPU_MIPS.decompileInst(tmp))
//            pc += 4
//        }
//    }
//    print("\n\n\n\n\n")
//
////     bios hexdump
//    rom.bios.use {
//        var index = 0
//        var lineCounter = 0
//        while (true) {
//            val r = it.read()
//            if (r == -1) break
//            if (lineCounter == 0) {
//                lineCounter = 16
//                print("\n 0x" + "%04x ".format(index))
//            }
//            print("%02x ".format(r))
//            lineCounter--
//
//            memory.writeByte(index++, r.toByte())
//        }
//        println("\nBios size: $index bytes")
//    }
//    print("\n\n\n\n\n")

    val display = MonitorWindow(monitor)
    val window = JFrame("Emulator")

    val box = Box(BoxLayout.Y_AXIS)
    box.alignmentX = JComponent.CENTER_ALIGNMENT
    box.add(Box.createVerticalGlue())
    box.add(display)
    box.add(Box.createVerticalGlue())
    window.contentPane.apply { background = Color.DARK_GRAY.darker().darker() }
    window.add(box)
    window.pack()
    window.setSize(811 + 16, 498 + 39)
    window.isVisible = true
    window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    window.addKeyListener(object : KeyListener {
        override fun keyTyped(e: KeyEvent) {
            val ibd = IBD()
            monitor.onKeyPressed(e.keyChar.toInt())
            monitor.saveToServer(ibd)
            monitor.loadFromClient(ibd)
        }

        override fun keyPressed(e: KeyEvent?) = Unit

        override fun keyReleased(e: KeyEvent?) = Unit
    })

    println("Start")

    motherboard.reset()
    cpu.debugLevel = 2
    motherboard.cyclesPerTick = 200000
    motherboard.start()
    timer = System.currentTimeMillis()
    while (motherboard.isOnline()) {
        motherboard.iterate()
        display.revalidate()
        display.repaint()
        timer = System.currentTimeMillis()
        val tick = (timer.and(0xFFFFFF) / 50L).toInt()
        if(tick != lastTick){
           lastTick = tick
        }
    }
    println("End")

    // This is used to avoid: 'Disconnected from the target VM' in the middle of the CPU output
    System.out.flush()
    Thread.sleep(10)

}

class MonitorWindow(val monitor: DeviceMonitor) : JPanel() {
    init {
        setSize(811, 498)
        maximumSize = Dimension(811, 498)
        minimumSize = Dimension(811, 498)
        preferredSize = Dimension(811, 498)
        background = Color.BLACK
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        val graphics = g as? Graphics2D ?: return
        val lines = monitor.lines
        val columns = monitor.columns
        var selected = false

        graphics.color = Color.GREEN
        for (line in 0..lines - 1) {
            for (column in 0..columns - 1) {
                val character = monitor.getChar(line * columns + column) and 0xFF
                if (line == monitor.cursorLine && column == monitor.cursorColumn && lastTick % 20 >= 10) {
                    selected = true
                }
                if (character != 0x20 || selected) {
                    val x = 10 * column + 5
                    val y = 14 * line + 15
                    if (selected) {
                        graphics.color = Color.GREEN
                        graphics.fillRect(x, y - 13, 10, 14)
                        graphics.color = Color.BLACK
                        graphics.drawString(String.format("%c", character.toChar()), x, y)
                        graphics.color = Color.GREEN
                    } else {
                        graphics.drawString(String.format("%c", character.toChar()), x, y)
                    }
                }
                selected = false
            }
        }
    }
}

object FakeRef : ITileRef {
    override fun getWorld(): World = error("Not available in emulation mode")
    override fun getPos(): BlockPos = error("Not available in emulation mode")
}