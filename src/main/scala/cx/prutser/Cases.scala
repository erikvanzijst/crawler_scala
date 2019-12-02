package cx.prutser

abstract class Notification

case class Email(sender: String, title: String, body: String) extends Notification

case class SMS(caller: String, message: String) extends Notification

case class VoiceRecording(contactName: String, link: String) extends Notification

object CasesMain {
  def main(args: Array[String]): Unit = {
    def show(n: Notification): Unit = {
      n match {
        case Email(sender, title, _) => println("Email received")
        case VoiceRecording(contactName, _) if contactName != null => println(s"Voicerecording from ${contactName}")
        case _ => println(s"Unknown notification: ${n}")
      }
    }

    show(Email("root@localhost", "What's up!", "Lorem ipsum"))
    show(SMS("650-522-0160", "Lorem ipsum"))
    show(VoiceRecording("Frans", "http://foo.bar"))
    show(VoiceRecording(null, "http://foo.bar"))

    abstract class Device
    case class Phone(model: String) extends Device {
      def screenOff = "Turning screen off"
    }
    case class Computer(model: String) extends Device {
      def screenSaverOn = "Turning screen saver on..."
    }

    def goIdle(device: Device) = device match {
      case Phone(_) => device.asInstanceOf[Phone].screenOff
      case c: Computer => c.screenSaverOn
    }

    println(goIdle(Phone("Samsung")))


    println(3 match {
      case 1 => "1"
      case _ => s"""The supplied
                   |value is not 1
                   |""".stripMargin
    })
  }
}