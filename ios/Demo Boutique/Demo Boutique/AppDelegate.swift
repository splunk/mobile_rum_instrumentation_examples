import UIKit
import SDWebImage
import SplunkOtel
import SplunkOtelCrashReporting

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        //set the realm and token

        SplunkRumBuilder(realm: "<realm>", rumAuth: "<rum-token>")
        // Call methods to configure environment name, app name, and if you want to see debug logs
        .deploymentEnvironment(environment: "<environment>")
        .setApplicationName("<your_app_name>").debug(enabled: true).build()

        //enable crash reporting

        SplunkRumCrashReporting.start()
        

        // Clear SDWebImage caches so the images get loaded on every app open
        SDImageCache.shared.clearMemory()
        SDImageCache.shared.clearDisk()
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }


}

