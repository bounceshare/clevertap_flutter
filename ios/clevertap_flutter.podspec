#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'clevertap_flutter'
  s.version          = '0.0.1'
  s.summary          = 'Flutter Plugin For Clevertap'
  s.description      = <<-DESC
Flutter Plugin For Clevertap
                       DESC
  s.homepage         = 'http://example.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Raja Earla' => 'earla.raja@gmail.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.dependency 'CleverTap-iOS-SDK'
  s.ios.deployment_target = '8.0'
end

