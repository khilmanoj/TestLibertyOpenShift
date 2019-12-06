FROM websphere-liberty:latest
COPY --chown=1001:0  TestServletWebProject.war /config/dropins/TestServletWebProject.war
COPY --chown=1001:0  server.xml /config/

ARG MP_MONITORING=true

RUN configure.sh